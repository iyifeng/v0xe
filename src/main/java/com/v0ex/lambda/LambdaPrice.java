package com.v0ex.lambda;

/**
 * @author yifeng
 * @date 18/12/10
 */
public class LambdaPrice {

    public static void main(String[] args) {
        //map to map
//        Map<String, Set<HisPriceDetailUpsert.PriceItem>> oldMap = tabStationHisPriceDetails
//                .stream()
//                .filter(Objects::nonNull)
//                .collect(Collectors.groupingBy(TabStationHisPriceDetail::getStationType,
//                        Collectors.collectingAndThen(Collectors.toList(),
//                                c -> c
//                                        .stream()
//                                        .filter(Objects::nonNull)
//                                        .filter(o -> !StringUtils.isEmpty(o.getStationLevel()) && null != o.getPrice())
//                                        .map(o -> {
//                                            HisPriceDetailUpsert.PriceItem priceItem = new HisPriceDetailUpsert.PriceItem();
//                                            priceItem.setStationLevel(o.getStationLevel());
//                                            priceItem.setPrice(o.getPrice());
//                                            return priceItem;
//                                        }).collect(Collectors.toSet()))));
    }
}
