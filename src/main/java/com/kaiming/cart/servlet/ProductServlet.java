package com.kaiming.cart.servlet;

import com.kaiming.cart.data.LocalCache;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品控制器
 */
@WebServlet(name = "ProductServlet")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            service(request,response);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("products", LocalCache.getProducts());
//        req.getRequestDispatcher("/WEB-INF/views/biz/list.jsp").forward(req,resp);


        String name = req.getParameter("title");

        String pageStr = req.getParameter("page");
        int page = 1;
        if (null != pageStr && !"".equals(pageStr)) {
            page = Integer.parseInt(pageStr);
        }

        int totalProducts = LocalCache.getProductsCount(name);
        int totalPage = totalProducts % 12 > 0 ? totalProducts / 12 + 1 : totalProducts / 12;

        req.setAttribute("curPage", page);
        req.setAttribute("prePage", page > 1 ? page - 1 : 1);
        req.setAttribute("nextPage", totalPage > page ? page + 1 : totalPage);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("title", name);


        req.setAttribute("products", LocalCache.getProducts(page, 12, name));
        req.getRequestDispatcher("/WEB-INF/views/biz/list.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
