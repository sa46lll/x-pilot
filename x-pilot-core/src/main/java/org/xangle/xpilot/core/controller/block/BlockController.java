package org.xangle.xpilot.core.controller.block;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.facade.block.BlockFacadeService;
import org.xangle.xpilot.core.model.request.BlockDetailRequest;
import org.xangle.xpilot.core.model.request.BlockListRequest;
import org.xangle.xpilot.core.model.response.BlockDetailInfo;
import org.xangle.xpilot.core.model.response.BlockListInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;
import org.xangle.xpilot.core.service.block.BlockService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/block")
public class BlockController {

    private final BlockService blockService;
    private final BlockFacadeService blockFacadeService;

    @GetMapping("/list")
    public PageableInfo<BlockListInfo> findAll(@RequestParam(required = false, defaultValue = "1") int page,
                                               @RequestParam(required = false, defaultValue = "5") int size) {
        return blockService.findAll(new BlockListRequest(page, size));
    }

    @GetMapping("/{blockNumber}")
    public BlockDetailInfo findByBlockNumber(@PathVariable Long blockNumber,
                                             @RequestParam(required = false, defaultValue = "1") int page,
                                             @RequestParam(required = false, defaultValue = "5") int size) {
        return blockFacadeService.findByBlockNumber(
                new BlockDetailRequest(blockNumber, page, size));
    }
}
