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
import org.xangle.xpilot.core.model.response.BlockSummaryDetailInfo;
import org.xangle.xpilot.core.model.response.BlockListInfo;
import org.xangle.xpilot.core.model.response.PageableInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class BlockController {

    private final BlockFacadeService blockFacadeService;

    /**
     * 블록 리스트 조회
     *
     * @param page
     * @param size
     * @return PageableInfo<BlockListInfo>
     */
    @GetMapping("/v1/block/list")
    public PageableInfo<BlockListInfo> findAll(@RequestParam(required = false, defaultValue = "1") int page,
                                               @RequestParam(required = false, defaultValue = "5") int size) {
        return blockFacadeService.getAll(new BlockListRequest(page, size));
    }

    /**
     * 블록 상세 조회 (트랜잭션 및 댓글 포함), root 기준 페이징
     *
     * @param blockNumber
     * @param page
     * @param size
     * @return BlockDetailInfo
     */
    @GetMapping("/v1/block/{blockNumber}")
    public BlockDetailInfo getByBlockNumber(@PathVariable Long blockNumber,
                                            @RequestParam(required = false, defaultValue = "1") int page,
                                            @RequestParam(required = false, defaultValue = "5") int size) {
        return blockFacadeService.getByBlockNumber(
                new BlockDetailRequest(blockNumber, page, size));
    }

    /**
     * 블록 상세 조회 (트랜잭션 및 댓글 포함), 대댓글 기준 페이징
     *
     * @param blockNumber
     * @param page
     * @param size
     * @return BlockSummaryDetailInfo
     */
    @GetMapping("/v2/block/{blockNumber}")
    public BlockSummaryDetailInfo getSummaryByBlockNumber(@PathVariable Long blockNumber,
                                                          @RequestParam(required = false, defaultValue = "1") int page,
                                                          @RequestParam(required = false, defaultValue = "5") int size) {
        return blockFacadeService.getSummaryByBlockNumber(
                new BlockDetailRequest(blockNumber, page, size));
    }
}
