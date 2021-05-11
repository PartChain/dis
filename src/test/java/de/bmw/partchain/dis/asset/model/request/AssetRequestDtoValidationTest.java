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

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;
import java.util.stream.Collectors;

import static de.bmw.partchain.dis.asset.model.request.AssetQualityStatus.*;
import static de.bmw.partchain.dis.asset.model.request.SerialNumberType.BATCH;
import static de.bmw.partchain.dis.asset.model.request.SerialNumberType.SINGLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssetRequestDtoValidationTest
{
    private AssetRequestDto asset;
    private Validator validator;

    @Before
    public void setUp()
    {
        asset = createRequestDto();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturer_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setManufacturer(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("manufacturer is a mandatory property therefore can not be null", x.get()
                .getMessage());
    }

    @Test
    public void manufacturer_setStringValue_returnStringValue()
    {
        //Arrange
        asset.setManufacturer("value");

        //Act
        String result = asset.getManufacturer();

        //Assert
        assertEquals("value", result);
    }

    @Test
    public void partNameManufacturer_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setPartNameManufacturer(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("partNameManufacturer is a mandatory property therefore can not be null", x.get()
                .getMessage());
    }

    @Test
    public void partNameManufacturer_setStringValue_returnStringValue()
    {
        //Arrange
        asset.setPartNameManufacturer("value");

        //Act
        String result = asset.getPartNameManufacturer();

        //Assert
        assertEquals("value", result);
    }

    @Test
    public void partNumberCustomer_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setPartNumberCustomer(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("partNumberCustomer is a mandatory property therefore can not be null", x.get()
                .getMessage());
    }

    @Test
    public void partNumberCustomer_setStringValue_returnStringValue()
    {
        //Arrange
        asset.setPartNumberCustomer("value");

        //Act
        String result = asset.getPartNumberCustomer();

        //Assert
        assertEquals("value", result);
    }

    @Test
    public void partNumberManufacturer_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setPartNumberManufacturer(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("partNumberManufacturer is a mandatory property therefore can not be null", x.get()
                .getMessage());
    }

    @Test
    public void partNumberManufacturer_setStringValue_returnStringValue()
    {
        //Arrange
        asset.setPartNumberManufacturer("value");

        //Act
        String result = asset.getPartNumberManufacturer();

        //Assert
        assertEquals("value", result);
    }

    @Test
    public void serialNumberManufacturer_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setSerialNumberManufacturer(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("serialNumberManufacturer is a mandatory property therefore can not be null", x.get()
                .getMessage());
    }

    @Test
    public void serialNumberManufacturer_setStringValue_returnStringValue()
    {
        //Arrange
        asset.setSerialNumberManufacturer("value");

        //Act
        String result = asset.getSerialNumberManufacturer();

        //Assert
        assertEquals("value", result);
    }

    @Test
    public void serialNumberCustomer_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setSerialNumberCustomer(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("serialNumberCustomer is a mandatory property therefore can not be null", x.get()
                .getMessage());
    }

    @Test
    public void serialNumberCustomer_setStringValue_returnStringValue()
    {
        //Arrange
        asset.setSerialNumberCustomer("value");

        //Act
        String result = asset.getSerialNumberCustomer();

        //Assert
        assertEquals("value", result);
    }

    @Test
    public void status_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setStatus(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("status is a mandatory property therefore can not be null", x.get()
                .getMessage());
    }

    @Test
    public void status_setStringValue_returnStringValue()
    {
        //Arrange
        asset.setStatus("value");

        //Act
        String result = asset.getStatus();

        //Assert
        assertEquals("value", result);
    }

    @Test
    public void serialNumberType_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setSerialNumberType(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("serialNumberType null or with unrecognizable value. Must be SINGLE or BATCH", x.get()
                .getMessage());
    }

    @Test
    public void serialNumberType_setSingleValue_returnSingleValue()
    {
        //Arrange
        asset.setSerialNumberType(SINGLE);

        //Act
        SerialNumberType result = asset.getSerialNumberType();

        //Assert
        assertEquals(SINGLE, result);
    }

    @Test
    public void serialNumberType_setBatchValue_returnBatchValue()
    {
        //Arrange
        asset.setSerialNumberType(BATCH);

        //Act
        SerialNumberType result = asset.getSerialNumberType();

        //Assert
        assertEquals(BATCH, result);
    }

    @Test
    public void qualityStatus_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setQualityStatus(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        Optional<ConstraintViolation<AssetRequestDto>> x = violations.stream()
                .findFirst();
        assertEquals(1, violations.size());
        assertTrue(x.isPresent());
        assertEquals("qualityStatus null or with unrecognizable value. Must be OK, NOK or FLAG", x.get()
                .getMessage());
    }

    @Test
    public void qualityStatus_setOkValue_returnOkValue()
    {
        //Arrange
        asset.setQualityStatus(OK);

        //Act
        AssetQualityStatus result = asset.getQualityStatus();

        //Assert
        assertEquals(OK, result);
    }

    @Test
    public void qualityStatus_setNokValue_returnNokValue()
    {
        //Arrange
        asset.setQualityStatus(NOK);

        //Act
        AssetQualityStatus result = asset.getQualityStatus();

        //Assert
        assertEquals(NOK, result);
    }

    @Test
    public void qualityStatus_setFlagValue_returnFlagValue()
    {
        //Arrange
        asset.setQualityStatus(FLAG);

        //Act
        AssetQualityStatus result = asset.getQualityStatus();

        //Assert
        assertEquals(FLAG, result);
    }

    @Test
    public void productionDateGmt_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setProductionDateGmt(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        List<String> violationsDescriptions = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        assertEquals(2, violationsDescriptions.size());
        assertTrue(violationsDescriptions.contains("productionDateGmt is a mandatory property therefore can not be null"));
        assertTrue(violationsDescriptions.contains("productionDateGmt is not a valid GMT date. The expected format is YYYY-MM-DDTHH:mm:SSZ"));
    }

    @Test
    public void productionDateGmt_setInvalidFormat_constraintViolationOccurs()
    {
        //Arrange
        asset.setProductionDateGmt("random string");

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        List<String> violationsDescriptions = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        assertEquals(1, violationsDescriptions.size());
        assertTrue(violationsDescriptions.contains("productionDateGmt is not a valid GMT date. The expected format is YYYY-MM-DDTHH:mm:SSZ"));
    }

    @Test
    public void productionDateGmt_setValidFormat_returnValue()
    {
        //Arrange
        asset.setProductionDateGmt("2002-02-12T12:37:23Z");

        //Act
        String result = asset.getProductionDateGmt();

        //Assert
        assertEquals("2002-02-12T12:37:23Z",result);
    }

    @Test
    public void productionCountryCodeManufacturer_setNull_constraintViolationOccurs()
    {
        //Arrange
        asset.setProductionCountryCodeManufacturer(null);

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        List<String> violationsDescriptions = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        assertEquals(2, violationsDescriptions.size());
        assertTrue(violationsDescriptions.contains("productionCountryCodeManufacturer is a mandatory property therefore can not be null"));
        assertTrue(violationsDescriptions.contains("productionCountryCodeManufacturer is not a valid ISO 3166-1 Alpha 2"));
    }

    @Test
    public void productionCountryCodeManufacturer_setInvalidFormat_constraintViolationOccurs()
    {
        //Arrange
        asset.setProductionCountryCodeManufacturer("random string");

        //Act
        Set<ConstraintViolation<AssetRequestDto>> violations = validator.validate(asset);

        //Assert
        List<String> violationsDescriptions = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        assertEquals(1, violationsDescriptions.size());
        assertTrue(violationsDescriptions.contains("productionCountryCodeManufacturer is not a valid ISO 3166-1 Alpha 2"));
    }

    @Test
    public void productionCountryCodeManufacturer_setValidFormat_returnValue()
    {
        //Arrange
        asset.setProductionCountryCodeManufacturer("RU");

        //Act
        String result = asset.getProductionCountryCodeManufacturer();

        //Assert
        assertEquals("RU", result);
    }



    private AssetRequestDto createRequestDto()
    {
        AssetRequestDto assetRequestDto = new AssetRequestDto();
        assetRequestDto.setManufacturer("");
        assetRequestDto.setPartNameManufacturer("");
        assetRequestDto.setPartNumberCustomer("");
        assetRequestDto.setPartNumberManufacturer("");
        assetRequestDto.setSerialNumberManufacturer("");
        assetRequestDto.setSerialNumberCustomer("");
        assetRequestDto.setStatus("");
        assetRequestDto.setSerialNumberType(SINGLE);
        assetRequestDto.setQualityStatus(OK);
        assetRequestDto.setProductionDateGmt("2000-01-01T00:00:00Z");
        assetRequestDto.setProductionCountryCodeManufacturer("AL");
        assetRequestDto.setManufacturerPlant("");
        assetRequestDto.setManufacturerLine("");
        assetRequestDto.setComponentsSerialNumbers(new HashSet<>());
        assetRequestDto.setCustomFields(new HashMap<>());
        assetRequestDto.setQualityDocuments(new HashMap<>());

        return assetRequestDto;
    }
}