<%-- 
    Document   : admin-orders
    Created on : Oct 21, 2018, 11:36:18 PM
    Author     : Shado
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="right-side mb-5">
    <div class="row title">
        <div class="col-md-12">
            <h6>
                Quản lý đơn hàng
            </h6>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-md-12">
            <table id="orders" table class="table table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã hóa đơn</th>
                        <th>Người nhận</th>
                        <th>Số điện thoại</th>
                        <th>Địa chỉ người nhận</th>
                        <th>Giá trị</th>
                        <th>Trạng thái</th>
                        
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${listOrder}" var="i" varStatus="no">
                    <tr>
                        <td>${no.index+1}</td>
                        <td>${i.id}</td>
                        <td>${accountDetailInfo.name}</td>
                        <td>${accountDetailInfo.mobile}</td>
                        <td>${accountDetailInfo.address}</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="3" value="${i.totalPrice}"/><sup>đ</sup></td>
                    <td class="text-center">
                        

                    <c:if test="${i.status == 1}">
                        <span class="active">Đang xử lý</span>
                    </c:if>
                    <c:if test="${i.status == 2}">
                        <span class="active">Đang giao hàng</span>
                    </c:if>
                    <c:if test="${i.status == 4}">
                        <span class="deactiveactive">Đang hủy đơn hàng</span>                                                   
                    </c:if>   
                    </td>
                    
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

