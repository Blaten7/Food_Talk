package com.icia.recipe.management.controller;

import com.icia.recipe.management.dao.InvenDao;
import com.icia.recipe.management.dto.FoodItemDto;
import com.icia.recipe.management.dto.InvenDto;
import com.icia.recipe.management.service.CommonService;
import com.icia.recipe.management.service.InvenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class InvenRestController {

    @Autowired
    private InvenService iSer;

    @Autowired
    private InvenDao iDao;

    @Autowired
    private CommonService cSer;

    @GetMapping("/inventory/list")
    public List<?> invenSelect(@RequestParam("currentMenu") String currentMenu, @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize) {
        log.info("[재고] 페번호: {}",pageNum);
        log.info("[재고] 페크기: {}",pageSize);
        log.info("[재고] 페이지이름: {}",currentMenu);
        List<?> invenList = null;
        switch (currentMenu) {
            case "재":
                invenList = iSer.getInvenList(pageNum, pageSize);
                break;
            case "발":
                invenList = iSer.getInvenAddList(pageNum, pageSize);
                break;
            case "유":
                invenList = iSer.getInvenList(pageNum, pageSize);
                break;
            case "폐":
                break;
            default :
                return null;
        }
        return invenList;
    }
    @GetMapping("/inventory/sort")
    public List<?> invenSort(@RequestParam("id") String id, @RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize") Integer pageSize) {
        switch (id.charAt(0)) {
            case 'f':
            case 'e':
                return iSer.getInvenListSort(id, pageNum, pageSize);
            case 'p':
                return iSer.getInvenAddListSort(id, pageNum, pageSize);
        }
        return null;
    }
    @PostMapping("/inventory/modalinput")
    public List<?> invenAdd(@RequestParam("company") String company, @RequestParam("name") String name,
                                   @RequestParam("count") String count, @RequestParam("price") String price,
                                   @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        log.info("[발주] 추가 진입");
        return iSer.insertInvenAdd(pageNum, pageSize, company, name, count, price);
    }
    @GetMapping("/inven/deletebox")
    public List<?> moveToDeleteBox(@RequestParam("deleteKeySet") ArrayList deleteKeySet, @RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize) {

        return iSer.deleteFromFooditem(deleteKeySet, pageNum, pageSize);

    }
    @GetMapping("/inventory/order")
    public List<InvenDto> finalOrder() {
        return iSer.finalOrder();
    }
    @GetMapping("/management/search")
    public List<?> searchModalDetails(@RequestParam("className") String className, @RequestParam("param") ArrayList param) {
        log.info("[검색] 상세모달 깐뜨롤러 진입");
        return cSer.getSearchModalDetails(className, param);
    }
}
