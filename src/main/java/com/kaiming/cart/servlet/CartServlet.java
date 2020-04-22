package com.kaiming.cart.servlet;

import com.kaiming.cart.data.Cart;
import com.kaiming.cart.data.LocalCache;
import com.kaiming.cart.data.Product;
//import jdk.vm.ci.meta.Local;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getServletPath());

        if(Objects.equals("/cart/cart.do",req.getServletPath())){
            String productId = req.getParameter("productId");
            System.out.println(productId);
            if(productId!=null) {
                Product product = LocalCache.getProduct(Long.valueOf(productId));
                LocalCache.addCart(product);
                System.out.println("添加成功");
            }
            System.out.println(req.getContextPath());
            resp.sendRedirect(req.getContextPath() + "/cart/list.do");


        }else if(Objects.equals("/cart/list.do",req.getServletPath())){
            req.setAttribute("carts",LocalCache.getCarts());
            req.getRequestDispatcher("/WEB-INF/views/biz/cart.jsp").forward(req,resp);


        }else if(Objects.equals("/cart/delete.do",req.getServletPath())){
            String productId = req.getParameter("productId");
            if(productId!=null){
                LocalCache.delCart(Long.valueOf(productId));
            }
            resp.sendRedirect(req.getContextPath() + "/cart/list.do");

        }else if(Objects.equals("/cart/incr.do",req.getServletPath())){
            String productId = req.getParameter("productId");
            if(productId!=null){
                LocalCache.incrCart(Long.valueOf(productId));
            }
            resp.sendRedirect(req.getContextPath() +"/cart/list.do");
        }else if(Objects.equals("/cart/decr.do",req.getServletPath())){
            String productId = req.getParameter("productId");
            if(productId!=null){
                LocalCache.decrCart(Long.valueOf(productId));
            }
            resp.sendRedirect(req.getContextPath() + "/cart/list.do");
        }else if(Objects.equals("/cart/settlement.do",req.getServletPath())){
            String[] carts = req.getParameterValues("carts");
            int totalPrice = 0;
            for (String cart:carts
                 ) {
                Cart c = LocalCache.getCart(Long.valueOf(cart));
                totalPrice += c.getTotalPrice();
                LocalCache.delCart(Long.valueOf(cart));
            }
            req.setAttribute("totalPrice",totalPrice);
            req.getRequestDispatcher("/WEB-INF/views/biz/settlement.jsp").forward(req,resp);
        }
    }
}
