/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

import entity.Account;
import entity.AccountDetail;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccountDetailModel;
import model.AccountModel;
import utils.StringUtil;

/**
 *
 * @author Shado
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Get info customer
            // Account
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String passwordCf = request.getParameter("txtPasswordConfirm");

            // Account Detail
            String name = request.getParameter("txtName");
            String mobile = request.getParameter("txtMobile");
            String address = request.getParameter("txtAddress");
            int gender = Integer.parseInt(request.getParameter("gender"));

            int idAccountDetail = 0;

            if (!StringUtil.isEmail(email)) {
                request.setAttribute("message", "Email không đúng định dạng");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                if (new AccountModel().isCheckDuplicateEmail(email)) {
                    request.setAttribute("message", "Email đăng ký đã tồn tại");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    if (StringUtil.validatePasswordConfirm(password, passwordCf)) {
                        AccountDetail accountDetail = new AccountDetail(name, mobile, gender, address);
                        idAccountDetail = new AccountDetailModel().addAccountDetail(accountDetail);
                        if (idAccountDetail > 0) {
                            Account account = new Account(email, password, idAccountDetail, 2, 1);
                            if (new AccountModel().addAccount(account)) {
                                request.setAttribute("message", "Đăng ký tài khoản thành công");
                                request.getRequestDispatcher("register.jsp").forward(request, response);
                            }
                        }
                    } else {
                        request.setAttribute("message", "Mật khẩu không đúng định dạng");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
