<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.danyayun.jpetstore.domain.Item" %>

<%
    Item item = new Item();
%>


<div class="col">
    <div class="card shadow-sm">
        <div style="display: none" class="item-id">${item.itemId}</div>
        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
            <rect width="100%" height="100%" fill="#55595c"/>
            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
        </svg>
        <div class="card-body">
            <p class="card-text"></p>
            <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary rounded-start-4 btn-view">
                        View
                    </button>
                    <button type="button"
                            class="btn btn-sm btn-outline-secondary rounded-end-4 btn-purchase"
                            id="btn-purchase">Purchase
                    </button>
                </div>
                <span class="badge rounded-pill p-2"><%= item.getListPrice() %> 元</span>
            </div>
        </div>
    </div>
</div>
