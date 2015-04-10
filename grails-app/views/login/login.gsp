<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>Login</title>
</head>
<body>
<div class="body">
    <g:form action="doLogin" method="post">
        <div class="dialog">
            <p>Logowanie:</p>
            <table class="userForm">
                <tr>
                    <td valign="middle" style="text-align: center">
                        <g:each in="${messages}" var="message">
                            ${message}<br>
                        </g:each>
                    </td>
                </tr>
                <tr class='prop'>
                    <td valign='top' style='text-align:left;' width='20%'>
                        <label for='login'>Login:</label>
                    </td>
                    <td valign='top' style='text-align:left;' width='80%'>
                        <input id="login" type='text' name='login' value='${user?.login}' />
                    </td>
                </tr>
                <tr class='prop'>
                    <td valign='top' style='text-align:left;' width='20%'>
                        <label for='password'>Hasło:</label>
                    </td>
                    <td valign='top' style='text-align:left;' width='80%'>
                        <input id="password" type='password' name='password'
                               value='${user?.password}' />
                    </td>
                </tr>

            </table>
        </div>
        <div class="buttons">
            <span class="formButton">
                <input type="submit" value="Login">
            </span>
        </div>
    </g:form>
</div>
</body>
</html>