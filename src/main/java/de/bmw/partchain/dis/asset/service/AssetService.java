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

package de.bmw.partchain.dis.asset.service;

import de.bmw.partchain.dis.asset.mapper.AssetMapper;
import de.bmw.partchain.dis.asset.model.gateway.Asset;
import de.bmw.partchain.dis.asset.model.request.AssetRequestDto;
import de.bmw.partchain.dis.asset.gateway.AssetGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AssetService
{
    private final AssetGateway ledgerGateway;
    private final AssetMapper mapper;

    public AssetService(AssetGateway ledgerGateway, AssetMapper mapper)
    {
        this.ledgerGateway = ledgerGateway;
        this.mapper = mapper;
    }

    public void sendToLedger(AssetRequestDto assetRequestDto, String requestProcessId)
    {
        Asset assetKafkaEntity = mapper.mapFrom(assetRequestDto);
        assetKafkaEntity.setRequestProcessId(requestProcessId);

        ledgerGateway.sendToLedger(assetKafkaEntity);
    }
}