<%-- 
    Document   : admin-category
    Created on : Mar 17, 2022, 8:38:22 PM
    Author     : 19longdt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="right-side mb-5">
    <div class="row title">
        <div class="col-md-12">
            <h6>
                Thêm loại hàng mới
            </h6>
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-md-6">
            <button class="btn btn-success" type="button" data-toggle="collapse" data-target="#addProduct" aria-expanded="false" aria-controls="collapseExample">
                Thêm loại hàng mới
            </button>
            <p class="mt-3">Ấn vào nút để thêm loại hàng.</p>
        </div>
        <div class="col-md-6">
            <span style="color: #ed1d27">${message}</span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="collapse mb-2" id="addProduct">
                <div class="card">
                    <div class="card-body">
                        <form action="manageCategory" method="post" acceptcharset="UTF-8">
                            <div class="row px-3">
                                <div class="form-group col-md-6">
                                    <input type="text" class="form-control" name="catename" placeholder="Tên loại hàng" required/>
                                </div>
                                <button class="btn btn-success ml-3" type="submit" href="manageCategory">Thêm</button>
                                <!--<input type="submit" class="btn btn-success ml-3" name="btnUpload" value="Thêm sản phẩm">-->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row title">
        <div class="col-md-12">
            <h6>
                Danh sách loại hàng
            </h6>
        </div>
    </div>  
    <div class="row mt-2">
        <div class="col-md-12">
            <table id="products" table class="table table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã loại hàng</th>
                        <th>Tên loại hàng</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${categories}" var="i" varStatus="no">
                    <tr>
                        <td>${no.index+1}</td>
                        <td>${i.id}</td>
                        <td><label style="width: 100%;" type="text" name="categoryName" value="${i.category}">${i.category}</label>
                        </td>
                        <!--data-toggle="modal" data-target="#myModal"--> 
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
