/*
 *  Copyright 2021 The PartChain Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package de.bmw.partchain.dis.asset.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.bmw.partchain.dis.asset.model.request.AssetQualityStatus;
import de.bmw.partchain.dis.asset.model.request.AssetRequestDto;
import de.bmw.partchain.dis.asset.model.request.SerialNumberType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
public class AssetRequestDtoDeserializer extends StdDeserializer<AssetRequestDto>
{
    private static final String MANUFACTURER = "manufacturer";
    private static final String PART_NAME_MANUFACTURER = "partNameManufacturer";
    private static final String PART_NUMBER_CUSTOMER = "partNumberCustomer";
    private static final String PART_NUMBER_MANUFACTURER = "partNumberManufacturer";
    private static final String PRODUCTION_COUNTRY_CODE_MANUFACTURER = "productionCountryCodeManufacturer";
    private static final String PRODUCTION_DATE_GMT = "productionDateGmt";
    private static final String MANUFACTURER_PLANT = "manufacturerPlant";
    private static final String MANUFACTURER_LINE = "manufacturerLine";
    private static final String SERIAL_NUMBER_TYPE = "serialNumberType";
    private static final String QUALITY_STATUS = "qualityStatus";
    private static final String SERIAL_NUMBER_CUSTOMER = "serialNumberCustomer";
    private static final String SERIAL_NUMBER_MANUFACTURER = "serialNumberManufacturer";
    private static final String STATUS = "status";
    private static final String COMPONENTS_SERIAL_NUMBERS = "componentsSerialNumbers";
    private static final String CUSTOM_FIELDS = "customFields";
    private static final String QUALITY_DOCUMENTS = "qualityDocuments";
    private final List<String> allAssetProperties =
            Arrays.asList(MANUFACTURER, PART_NAME_MANUFACTURER,
                    PART_NUMBER_CUSTOMER, PART_NUMBER_MANUFACTURER, PRODUCTION_COUNTRY_CODE_MANUFACTURER,
                    PRODUCTION_DATE_GMT, MANUFACTURER_PLANT, MANUFACTURER_LINE, SERIAL_NUMBER_TYPE, QUALITY_STATUS,
                    SERIAL_NUMBER_CUSTOMER, SERIAL_NUMBER_MANUFACTURER, STATUS, COMPONENTS_SERIAL_NUMBERS,
                    CUSTOM_FIELDS, QUALITY_DOCUMENTS);
    private final List<String> stringAssetProperties =
            Arrays.asList(MANUFACTURER, PART_NAME_MANUFACTURER,
                    PART_NUMBER_CUSTOMER, PART_NUMBER_MANUFACTURER, PRODUCTION_COUNTRY_CODE_MANUFACTURER,
                    PRODUCTION_DATE_GMT, MANUFACTURER_PLANT, MANUFACTURER_LINE,
                    SERIAL_NUMBER_CUSTOMER, SERIAL_NUMBER_MANUFACTURER, STATUS);

    public AssetRequestDtoDeserializer()
    {
        this(null);
    }

    public AssetRequestDtoDeserializer(Class<?> vc)
    {
        super(vc);
    }

    @Override
    public AssetRequestDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws
            IOException
    {
        JsonNode node = jsonParser
                .getCodec()
                .readTree(jsonParser);

        Iterable<String> iterable = node::fieldNames;
        List<String> jsonProperties = StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

        Set<String> invalidPropertiesException = checkNonExistentPropertiesInJsonDataModel(jsonProperties);

        AssetRequestDto asset = new AssetRequestDto();
        stringAssetProperties
                .forEach(property ->
                        loadStringProperties(node, asset, property));
        asset.setSerialNumberType(getSerialNumberType(node));
        asset.setQualityStatus(getQualityStatus(node));
        asset.setComponentsSerialNumbers(getComponentsSerialNumbers(node));
        asset.setQualityDocuments(loadMapField(node, QUALITY_DOCUMENTS));
        asset.setCustomFields(loadMapField(node, CUSTOM_FIELDS));


        asset.setWarnings(invalidPropertiesException);
        return asset;
    }

    private Set<String> checkNonExistentPropertiesInJsonDataModel(List<String> propertiesInJson)
    {
        return propertiesInJson
                .stream()
                .filter(element -> !allAssetProperties.contains(element))
                .map(element -> String.format("The property '%s' is not defined on the asset json schema", element))
                .collect(Collectors.toSet());
    }

    @SneakyThrows
    private void loadStringProperties(JsonNode node, AssetRequestDto asset, String fieldName)
    {
        if (node.has(fieldName) && !node.get(fieldName)
                .isNull())
        {
            String fieldValue = node.get(fieldName)
                    .asText();
            log.debug(String.format("set value for: %s with value: %s", fieldName, fieldValue));
            Class<?> clazz = asset.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(asset, fieldValue);
        }
    }

    private SerialNumberType getSerialNumberType(JsonNode node)
    {
        SerialNumberType result = SerialNumberType.SINGLE;
        if (node.has(SERIAL_NUMBER_TYPE) && !node.get(SERIAL_NUMBER_TYPE)
                .isNull())
        {
            try
            {
                String fieldValue = node.get(SERIAL_NUMBER_TYPE)
                        .textValue()
                        .toUpperCase();
                result = SerialNumberType.valueOf(fieldValue);
            }
            catch (IllegalArgumentException ignored)
            {
                result = null;
            }
        }
        log.debug(String.format("set value for: %s with value: %s", SERIAL_NUMBER_TYPE, result));
        return result;
    }

    private AssetQualityStatus getQualityStatus(JsonNode node)
    {
        AssetQualityStatus result = null;
        if (node.has(QUALITY_STATUS) && !node.get(QUALITY_STATUS)
                .isNull())
        {
            try
            {
                String fieldValue = node.get(QUALITY_STATUS)
                        .textValue()
                        .toUpperCase();
                result = AssetQualityStatus.valueOf(fieldValue);
            }
            catch (IllegalArgumentException ignored)
            {
            }
        }
        log.debug(String.format("set value for: %s with value: %s", QUALITY_STATUS, result));
        return result;
    }

    private Set<String> getComponentsSerialNumbers(JsonNode node)
    {
        Set<String> result = null;
        if (node.has(COMPONENTS_SERIAL_NUMBERS) && !node.get(COMPONENTS_SERIAL_NUMBERS)
                .isNull())
        {
            result = new HashSet<>();
            for (JsonNode child : node.get(COMPONENTS_SERIAL_NUMBERS))
            {
                result.add(child.textValue());
            }

            log.debug(String.format("set %s with %s distinct serial numbers", COMPONENTS_SERIAL_NUMBERS,
                    result.size()));
        }
        return result;
    }

    @SneakyThrows
    private Map<String, String> loadMapField(JsonNode node, String fieldName)
    {
        Map<String, String> result = null;
        if (node.has(fieldName) && !node.get(fieldName)
                .isNull())
        {
            JsonNode child = node.get(fieldName);
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.convertValue(child, new TypeReference<Map<String, String>>()
            {
            });
            log.debug(String.format("set %s with %s elements", fieldName, result.size()));
        }

        return result;
    }
}


