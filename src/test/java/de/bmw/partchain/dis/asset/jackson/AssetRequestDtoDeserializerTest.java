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

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bmw.partchain.dis.asset.model.request.AssetQualityStatus;
import de.bmw.partchain.dis.asset.model.request.AssetRequestDto;
import de.bmw.partchain.dis.asset.model.request.SerialNumberType;
import lombok.SneakyThrows;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssetRequestDtoDeserializerTest
{
    @Test
    @SneakyThrows
    public void serialNumberType_SingleInJson_SingleInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithSerialNumberType("SINGLE");

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertEquals(SerialNumberType.SINGLE, asset.getSerialNumberType());
    }

    @Test
    @SneakyThrows
    public void serialNumberType_BatchInJson_BatchInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithSerialNumberType("BATCH");

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertEquals(SerialNumberType.BATCH, asset.getSerialNumberType());
    }

    @Test
    @SneakyThrows
    public void serialNumberType_NullInJson_SingleInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithSerialNumberType(null);

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertEquals(SerialNumberType.SINGLE, asset.getSerialNumberType());
    }

    @Test
    @SneakyThrows
    public void serialNumberType_InvalidValueInJson_NullInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithSerialNumberType("invalidValue");

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNull(asset.getSerialNumberType());
    }

    @Test
    @SneakyThrows
    public void qualityStatus_OkInJson_OkInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithQualityStatus("OK");

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertEquals(AssetQualityStatus.OK, asset.getQualityStatus());
    }

    @Test
    @SneakyThrows
    public void qualityStatus_NokInJson_NokInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithQualityStatus("NOK");

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertEquals(AssetQualityStatus.NOK, asset.getQualityStatus());
    }

    @Test
    @SneakyThrows
    public void qualityStatus_FlagInJson_FlagInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithQualityStatus("FLAG");

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertEquals(AssetQualityStatus.FLAG, asset.getQualityStatus());
    }

    @Test
    @SneakyThrows
    public void qualityStatus_NullInJson_nullInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithQualityStatus(null);

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNull(asset.getQualityStatus());
    }

    @Test
    @SneakyThrows
    public void qualityStatus_InvalidInJson_nullInAsset()
    {
        //Arrange
        final String json = getAssetJsonWithQualityStatus(null);

        //Act
        AssetRequestDto asset = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNull(asset.getQualityStatus());
    }


    @Test
    @SneakyThrows
    public void unknownProperty_unknownPropertyInJson_assetCreated()
    {
        //Arrange
        final String json = "{\"somethingNew\": \"somethingNewValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
    }

    @Test
    @SneakyThrows
    public void partNumberManufacturer_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getPartNumberManufacturer());
    }

    @Test
    @SneakyThrows
    public void partNumberManufacturer_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNumberManuufacturer\": null," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getPartNumberManufacturer());
    }

    @Test
    @SneakyThrows
    public void serialNumberCustomer_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturer\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getSerialNumberCustomer());
    }

    @Test
    @SneakyThrows
    public void serialNumberCustomer_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturer\"," +
                "\"serialNumberCustomer\": null," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getSerialNumberCustomer());
    }

    @Test
    @SneakyThrows
    public void partNameManufacturer_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getPartNameManufacturer());
    }

    @Test
    @SneakyThrows
    public void partNameManufacturer_nullJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"partNameManufacturer\": null," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getPartNameManufacturer());
    }

    @Test
    @SneakyThrows
    public void qualityStatus_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getQualityStatus());
    }

    @Test
    @SneakyThrows
    public void qualityStatus_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"qualityStatus\": null," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getQualityStatus());
    }

    @Test
    @SneakyThrows
    public void productionCountryCodeManufacturer_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getProductionCountryCodeManufacturer());
    }

    @Test
    @SneakyThrows
    public void productionCountryCodeManufacturer_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"productionCountryCodeManufacturer\": null," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getProductionCountryCodeManufacturer());
    }

    @Test
    @SneakyThrows
    public void serialNumberManufacturer_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getSerialNumberManufacturer());
    }

    @Test
    @SneakyThrows
    public void serialNumberManufacturer_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberManufacturer\": null," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getSerialNumberManufacturer());
    }

    @Test
    @SneakyThrows
    public void manufacturer_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getManufacturer());
    }

    @Test
    @SneakyThrows
    public void manufacturer_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"manufacturer\": null," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getManufacturer());
    }

    @Test
    @SneakyThrows
    public void partNumberCustomer_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getPartNumberCustomer());
    }

    @Test
    @SneakyThrows
    public void partNumberCustomer_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNumberCustomer\": null," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getPartNumberCustomer());
    }

    @Test
    @SneakyThrows
    public void status_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getStatus());
    }

    @Test
    @SneakyThrows
    public void status_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"status\": null," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getStatus());
    }

    @Test
    @SneakyThrows
    public void componentsSerialNumbers_missingInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getComponentsSerialNumbers());
    }

    @Test
    @SneakyThrows
    public void componentsSerialNumbers_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"componentsSerialNumbers\": null," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getComponentsSerialNumbers());
    }

    @Test
    @SneakyThrows
    public void componentsSerialNumbers_2duplicatedSerialNumbersInJson_onlyOneInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"componentsSerialNumbers\": [\"componentSerialNumber1\",\"componentSerialNumber1\"]," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNotNull(result.getComponentsSerialNumbers());
        assertEquals(1, result.getComponentsSerialNumbers()
                .size());
        assertTrue(result.getComponentsSerialNumbers()
                .contains("componentSerialNumber1"));
    }

    @Test
    @SneakyThrows
    public void componentsSerialNumbers_differentSerialNumbersInJson_allPresentInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"componentsSerialNumbers\": [\"componentSerialNumber1\",\"componentSerialNumber2\"]," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNotNull(result.getComponentsSerialNumbers());
        assertEquals(2, result.getComponentsSerialNumbers()
                .size());
        assertTrue(result.getComponentsSerialNumbers()
                .contains("componentSerialNumber1"));
        assertTrue(result.getComponentsSerialNumbers()
                .contains("componentSerialNumber2"));
    }
    @Test
    @SneakyThrows
    public void qualityDocuments_missingInJson_nullInAsset(){
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getQualityDocuments());

    }

    @Test
    @SneakyThrows
    public void qualityDocuments_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"qualityDocuments\": null," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getQualityDocuments());
    }

    @Test
    @SneakyThrows
    public void qualityDocuments_duplicatedKeysInJson_singleKeyWithLastValueInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"qualityDocuments\":{\"qualityDocumentKey\":\"qualityValue1\",\"qualityDocumentKey" +
                "\":\"qualityValue2\"}," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNotNull(result.getQualityDocuments());
        assertEquals(1,result.getQualityDocuments().size());
        assertEquals("qualityValue2",result.getQualityDocuments().get("qualityDocumentKey"));
    }

    @Test
    @SneakyThrows
    public void qualityDocuments_distinctKeysInJson_distinctKeysInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"qualityDocuments\":{\"qualityDocumentKey1\":\"qualityValue1\",\"qualityDocumentKey2" +
                "\":\"qualityValue2\"}," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNotNull(result.getQualityDocuments());
        assertEquals(2,result.getQualityDocuments().size());
        assertEquals("qualityValue1",result.getQualityDocuments().get("qualityDocumentKey1"));
        assertEquals("qualityValue2",result.getQualityDocuments().get("qualityDocumentKey2"));
    }

    @Test
    @SneakyThrows
    public void customFields_missingInJson_nullInAsset(){
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getCustomFields());
    }

    @Test
    @SneakyThrows
    public void customFields_nullInJson_nullInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"customFields\": null," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNull(result.getCustomFields());
    }

    @Test
    @SneakyThrows
    public void customFields_duplicatedKeysInJson_singleKeyWithLastValueInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"customFields\":{\"customFieldsKey\":\"customFields1\",\"customFieldsKey" +
                "\":\"customFields2\"}," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNotNull(result.getCustomFields());
        assertEquals(1,result.getCustomFields().size());
        assertEquals("customFields2",result.getCustomFields().get("customFieldsKey"));
    }

    @Test
    @SneakyThrows
    public void customFields_distinctKeysInJson_distinctKeysInAsset()
    {
        //Arrange
        final String json = "{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"customFields\":{\"customFieldsKey1\":\"customFieldsValue1\",\"customFieldsKey2" +
                "\":\"customFieldsValue2\"}," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"}";

        //Act
        AssetRequestDto result = new ObjectMapper().readValue(json, AssetRequestDto.class);

        //Assert
        assertNotNull(result);
        assertNotNull(result.getCustomFields());
        assertEquals(2,result.getCustomFields().size());
        assertEquals("customFieldsValue1",result.getCustomFields().get("customFieldsKey1"));
        assertEquals("customFieldsValue2",result.getCustomFields().get("customFieldsKey2"));
    }


    private String getAssetJsonWithSerialNumberType(String value)
    {
        String serialNumberType = value == null ?
                "null" :
                String.format("\"%s\"", value);

        return String.format("{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"serialNumberType\": %s}", serialNumberType);
    }



    private String getAssetJsonWithQualityStatus(String value)
    {
        String qualityStatus = value == null ?
                "null" :
                String.format("\"%s\"", value);

        return String.format("{\"partNumberManufacturer\": \"partNumberManufacturerValue\"," +
                "\"serialNumberCustomer\": \"serialNumberCustomerValue\"," +
                "\"partNameManufacturer\": \"partNameManufacturerValue\"," +
                "\"qualityStatus\": \"qualityStatusValue\"," +
                "\"productionCountryCodeManufacturer\": \"productionCountryCodeManufacturerValue\"," +
                "\"serialNumberManufacturer\": \"serialNumberManufacturerValue\"," +
                "\"manufacturer\": \"manufacturerValue\"," +
                "\"partNumberCustomer\": \"partNumberCustomerValue\"," +
                "\"status\": \"statusValue\"," +
                "\"qualityStatus\": %s}", qualityStatus);
    }
}