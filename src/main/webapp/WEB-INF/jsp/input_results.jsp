
<html>
<body>



    <li th:each="item : ${searchItems}">
        <a class="dropdown-item rounded-4" href="#" th:text="${item.product.name}"></a>
    </li>


</body>


</html>
