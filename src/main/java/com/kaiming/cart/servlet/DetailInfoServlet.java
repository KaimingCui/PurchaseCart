package com.kaiming.cart.servlet;

import com.kaiming.cart.data.LocalCache;
import com.kaiming.cart.data.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "DetailInfoServlet")
public class DetailInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.equals(req.getServletPath(), "/detail/detail.do")) {
            String productId = req.getParameter("productId");
            if (productId != null) {
//                Cookie cookie = new Cookie("productId");
                Product product = LocalCache.getProduct(Long.valueOf(productId));
                req.setAttribute("product",product);
                LocalCache.addBrowseLog(product);
            }

            req.getRequestDispatcher("/WEB-INF/views/biz/detail.jsp").forward(req,resp);
        }
    }
}
