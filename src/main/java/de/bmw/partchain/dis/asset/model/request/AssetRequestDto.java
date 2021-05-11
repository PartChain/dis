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

package de.bmw.partchain.dis.asset.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.bmw.partchain.dis.asset.jackson.AssetRequestDtoDeserializer;
import de.bmw.partchain.dis.asset.validator.IsDateGMT;
import de.bmw.partchain.dis.asset.validator.IsISO3166_1Alpha2CountryCode;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

@Data
@JsonDeserialize(using = AssetRequestDtoDeserializer.class)
public class AssetRequestDto
{
    @NotNull(message = "manufacturer is a mandatory property therefore can not be null")
    private String manufacturer;
    @NotNull(message = "partNameManufacturer is a mandatory property therefore can not be null")
    private String partNameManufacturer;
    @NotNull(message = "partNumberCustomer is a mandatory property therefore can not be null")
    private String partNumberCustomer;
    @NotNull(message = "partNumberManufacturer is a mandatory property therefore can not be null")
    private String partNumberManufacturer;
    @NotNull(message = "serialNumberManufacturer is a mandatory property therefore can not be null")
    private String serialNumberManufacturer;
    @NotNull(message = "serialNumberCustomer is a mandatory property therefore can not be null")
    private String serialNumberCustomer;
    @NotNull(message = "status is a mandatory property therefore can not be null")
    private String status;
    @NotNull(message = "serialNumberType null or with unrecognizable value. Must be SINGLE or BATCH")
    private SerialNumberType serialNumberType;
    @NotNull(message = "qualityStatus null or with unrecognizable value. Must be OK, NOK or FLAG")
    private AssetQualityStatus qualityStatus;
    @NotNull(message = "productionDateGmt is a mandatory property therefore can not be null")
    @IsDateGMT(message = "productionDateGmt is not a valid GMT date. The expected format is YYYY-MM-DDTHH:mm:SSZ")
    private String productionDateGmt;
    @NotNull(message = "productionCountryCodeManufacturer is a mandatory property therefore can not be null")
    @IsISO3166_1Alpha2CountryCode(message = "productionCountryCodeManufacturer is not a valid ISO 3166-1 Alpha 2")
    private String productionCountryCodeManufacturer;
    private String manufacturerPlant;
    private String manufacturerLine;
    private Set<String> componentsSerialNumbers;
    private Map<String, String> customFields;
    private Map<String, String> qualityDocuments;

   @JsonIgnore
   private Set <String> warnings;
}
