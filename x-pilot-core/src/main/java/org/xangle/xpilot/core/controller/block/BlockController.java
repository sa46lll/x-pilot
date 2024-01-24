package org.xangle.xpilot.core.controller.block;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.model.request.BlockListInfo;
import org.xangle.xpilot.core.model.response.BlockListResponse;
import org.xangle.xpilot.core.model.response.GlobalPageResponse;
import org.xangle.xpilot.core.service.block.BlockService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/block")
public class BlockController {

    private final BlockService blockService;

    @GetMapping("/list")
    public GlobalPageResponse<BlockListResponse> findAll(@RequestBody final BlockListInfo blockListInfo) {
        return blockService.findAll(blockListInfo);
    }
}
