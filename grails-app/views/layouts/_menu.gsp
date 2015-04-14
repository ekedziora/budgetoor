<%@ page contentType="text/html;charset=UTF-8" %>
<g:if test="${session.user?.admin}">
    <g:link controller="user">Użytkownicy</g:link>
</g:if>
<g:if test="${session.user}">
    <g:link controller="login" action="logout">Wyloguj</g:link>
</g:if>
<g:else>
    <g:link controller="login" action="login">Zaloguj</g:link>
</g:else>