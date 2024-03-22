

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<!-- forEach loop creates cards for every Product.class available in the database -->
<div th:each="item : ${searchItems}">
  <!-- Card view contains information about Product.class -->
  <div class="col" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
    <div class="card shadow-sm">

      <!-- Actual image -->
      <img class="bd-placeholder-img card-img-top image-holder crop-image"
           th:src="${item.attribute2}" width="100%" height="225">
      <div class="card-body">
        <p class="card-text mb-0" th:text="${item.product.name}"></p>
        <p class="text-secondary p-0 mt-1" th:text="${item.attribute1}"></p>
        <div class="d-flex justify-content-between align-items-center">
          <div class="btn-group">
            <button type="button" class="btn btn-sm btn-outline-secondary rounded-start-4"
                    th:attr="onclick=|window.open('/item?itemId=${item.itemId}', '_blank')|"
                    th:text="View"></button>
            <button type="button" class="btn btn-sm btn-outline-secondary rounded-end-4 btn-purchase"
                    th:text="Purchase"></button>
          </div>
          <span class="badge rounded-pill p-2">

                        </span>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
