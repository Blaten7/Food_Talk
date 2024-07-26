package com.icia.recipe.management.dao;

import com.icia.recipe.management.dto.FoodItemDto;
import com.icia.recipe.management.dto.ImgDto;
import com.icia.recipe.management.dto.InvenDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface InvenDao {
    List<FoodItemDto> getInvenList();

    List<FoodItemDto> getSortedInvenList(String param, String sort);

    boolean deleteFromFoodItem(ArrayList deletekeySet);

    boolean insertToDeleteBox(String fcnum, String fcnum2, String ftitle, String fcontents, String fprice,
                              String fcount, String fdate, String fedate, String fviews, String fvolume,
                              String forigin, String fcal, String fsave, long ipath, long sysname,
                              long oriname, String mid, int fnum, String fileSize, String regDate);

    List<FoodItemDto> getFoodItemList(ArrayList deletekeySet);

    List<ImgDto> getImg(ArrayList deletekeySet);

    boolean insertInvenAdd(String count, String company, String name, String price);

    List<InvenDto> getInvenAddList();

    boolean finalOrder();

    List<InvenDto> getInvenAddListSort(String param, String sort);

    List<InvenDto> getSearchModalDetails(String company, String iname);
}
