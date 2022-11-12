<!DOCTYPE html>
<html lang="EN">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on("click", "#submit-button", function () {
                console.log("Debug");
                $.post("/", function (response) {
                    $("#ajax-div").text(response);
                })
            }
        )
    </script>

<#--    <script>-->
<#--        $(document).ready(() => {-->
<#--            let form = $("add-city-form")-->
<#--            let cityNameInput = form.find("[name='city']")-->

<#--            form.submit(() => {-->

<#--                let city = {-->
<#--                    name: cityNameInput.val()-->
<#--                }-->

<#--                $.ajax({-->
<#--                    url: "/",-->
<#--                    method: "POST",-->
<#--                    data: JSON.stringify(city.name),-->
<#--                    contentType: "application/json",-->
<#--                    complete: function (res) {-->
<#--                        console.log(res)-->
<#--                        var data =-->
<#--                    }-->
<#--                })-->
<#--                }-->
<#--            )-->
<#--        })-->
<#--    </script>-->
</head>

<body>
<h1>
    Weather
</h1>
<br>
<h1>
    <div id="ajax-div">
    </div>
</h1>
<form id="add-city-form" method="post" action="/" enctype="application/x-www-form-urlencoded" >
    <label for="city"></label>
    <input type="text" name="city" id="city">
    <br><br>
    <div>
        <input type="submit" id="submit-button" value="submit">
    </div>
    <br>
</form>


<#--<#macro content>-->
<#--    <div class="weather">-->
<#--        ${weather}<br>-->
<#--    </div>-->
<#--    <a href="/logout">Logout</a>-->
<#--</#macro>-->

</body>

</html>