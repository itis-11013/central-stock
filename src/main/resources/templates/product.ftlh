<#import "components/navbar.ftlh" as navbar />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<@navbar.spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
    <title>Bank</title>
</head>
<body>
<@navbar.navbar activeIndex=3 />
<div>
    <table class="table table-primary table-hover">
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Seller</th>
            <th>Product Catalog Code</th>
        </tr>
        <#list pagedResponse.content as product>
            <tr>
                <td>${product.innerId}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.count} ${product.unit} left</td>
                <td>${product.sellerId}</td>
                <td>${product.code}</td>
            </tr>
        </#list>
    </table>
</div>

<div class="footer">
    <button onclick="location.href='/central/admin/product?page=${pagedResponse.currentPage-1}'" class="btn btn-primary <#if pagedResponse.currentPage lte 1>disabled</#if>"> &lt; </button>
    <span>${pagedResponse.currentPage}</span>
    <button onclick="location.href='/central/admin/product?page=${pagedResponse.currentPage+1}'" class="btn btn-primary <#if pagedResponse.currentPage gte pagedResponse.totalPages>disabled</#if>"> &gt; </button>
</div>
</body>
</html>