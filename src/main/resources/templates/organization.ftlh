<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank</title>
</head>
<body>
<div>
    <nav>
        <div>
            <a href="/central/admin/bank">Bank</a>
            <a href="/central/admin/organization" class="here">Organization</a>
            <a href="/central/admin/contract" >Contract</a>
            <a href="/central/admin/product" >Product</a>
        </div>
    </nav>
</div>
<div>
    <table>
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Country Code</th>
        </tr>
        <#list list as singleBank>
        <tr>
            <td>${singleBank.innerId}</td>
            <td>${singleBank.name}</td>
            <td>${singleBank.address}</td>
            <td>${singleBank.countryCode}</td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>