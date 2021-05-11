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

package de.bmw.partchain.dis.asset.mapper;

import de.bmw.partchain.dis.asset.model.gateway.Asset;
import de.bmw.partchain.dis.asset.model.request.AssetRequestDto;
import de.bmw.partchain.dis.security.AuthenticationFacade;
import de.bmw.partchain.dis.security.AuthenticationFacadeImpl;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(componentModel = "spring")
public abstract class AssetMapper
{
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Mapping(target = "requestProcessId", ignore = true)
    @Mapping(target = "requestDate", ignore = true)
    @Mapping(target = "mspId", ignore = true)
    public abstract Asset mapFrom(AssetRequestDto asset);

    @AfterMapping
    public void setMspId(@MappingTarget Asset asset)
    {
        asset.setRequestDate(new Date());
        asset.setMspId(authenticationFacade.getOtherClaim(AuthenticationFacadeImpl.MSP_ID));
    }
}