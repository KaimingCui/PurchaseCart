package com.kaiming.cart.data;

import java.util.*;

/**
 * 本地缓存
 */
public final class LocalCache {

    private static Map<Long,Product> productMap = new HashMap<>();
    private static Map<Long,Cart> cartMap = new HashMap<>();
    private static Map<Long,Product> favoriteMap = new HashMap<>();
    private static Map<Long,Product> browseLogMap = new HashMap<>();


    static{
        productMap.put(1L, new Product(1L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(2L, new Product(2L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(3L, new Product(3L, "JAVA", "JAVA基础课程-基本语法", "介绍java基本语法特性及编写规范", "初级", 219));
        productMap.put(4L, new Product(4L, "JAVA", "JAVA基础课程-JDBC", "介绍JDBC方式连接数据库", "初级", 219));
        productMap.put(5L, new Product(5L, "JAVA", "JAVA基础课程—Socket", "介绍Java网络编程Socket", "初级", 219));
        productMap.put(6L, new Product(6L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(7L, new Product(7L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(8L, new Product(8L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(9L, new Product(9L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(10L, new Product(10L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(11L, new Product(11L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(12L, new Product(12L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(13L, new Product(13L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(14L, new Product(14L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(15L, new Product(15L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(16L, new Product(16L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(17L, new Product(17L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(18L, new Product(18L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(19L, new Product(19L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(20L, new Product(20L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(21L, new Product(21L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(22L, new Product(22L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(23L, new Product(23L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(24L, new Product(24L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
        productMap.put(25L, new Product(25L, "HTML/CSS", "HTML+CSS基础课程", "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义", "初级", 219));
    }

    //不允许在外部被实例化，只能用类名.static方法了
    private LocalCache(){

    }

    public static List<Product> getProducts(){
        //不好的方式，会修改原始数据
        //return productMap.values();
        return new ArrayList<>(productMap.values());
    }

    public static Product getProduct(Long id){
        return productMap.get(id);
        //new product(.....)
    }

    public static List<Cart> getCarts(){
        return new ArrayList<>(cartMap.values());
    }

    public static void addCart(Product product){
        if(cartMap.containsKey(product.getId())){
            incrCart(product.getId());
        }else {
            cartMap.put(product.getId(), new Cart(product.getId(), product.getId(), product.getName(), product.getPrice(),1));
        }
    }

    public static void incrCart(Long productId){
        cartMap.get(productId).incrCount();
    }

    public static void decrCart(Long productId){
        boolean result = cartMap.get(productId).decrCount();
        if(result){
            decrCart(productId);
        }
    }

    public static void delCart(Long productId){
        cartMap.remove(productId);
    }

    public static Cart getCart(Long cartId){
        return cartMap.get(cartId);
    }

    public static void addFavorite(Product product){
        if(!favoriteMap.containsKey(product.getId())){
            favoriteMap.put(product.getId(),product);
        }
    }

    public static void delFavorite(Product product){
        if(favoriteMap.containsKey(product.getId())){
            favoriteMap.remove(product.getId());
        }
    }

    public static List<Product> getFavorite(){
        return new ArrayList<>(favoriteMap.values());
    }

    public static void addBrowseLog(Product product){
        browseLogMap.put(product.getId(), product);
    }

    public static List<Product> getBrowseLogs(){
        return new ArrayList<>(browseLogMap.values());
    }

    public static void deleteBrowseLog(Long productId){
        if(browseLogMap.containsKey(productId)){
            browseLogMap.remove(productId);
        }
    }

    public static List<Product> getProducts(int page,int size,String name){
        List<Product> products = new ArrayList<>();

        if (null != name && !"".equals(name)) {
            Collection<Product> allp= productMap.values();
            for (Product p:allp
            ) {
                if(p.getName().contains(name)){
                    products.add(p);
                }
            }
        } else {
            products.addAll(productMap.values());
        }

        int start = (page - 1) * size;
        int end = products.size() >= page * size ? page * size : products.size();
        return products.subList(start, end);
    }

    public static int getProductsCount(String name){
        List<Product> products = new ArrayList<>();
        
        if(name != null && !"".equals(name)){
            Collection<Product> allp= productMap.values();
            for (Product p:allp
                 ) {
               if(p.getName().contains(name)){
                   products.add(p);
               }
            }
        }else{
            products.addAll(productMap.values());
        }

        return products.size();
    }
}
