<%@ page import="budgetoor.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.button.edit.password.label"/></title>
</head>
<body>
<a href="#edit-password" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="edit-password" class="content scaffold-edit" role="main">
    <h1><g:message code="default.button.edit.password.label"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${changePasswordCommandInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${changePasswordCommandInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form url="[bean: changePasswordCommandInstance, action:'updatePassword']" method="PUT" >
        <g:hiddenField name="userId" value="${changePasswordCommandInstance?.userId}" />
        <fieldset class="form">
            <div class="fieldcontain ${hasErrors(bean: changePasswordCommandInstance, field: 'newPassword', 'error')} required">
                <label for="newPassword">
                    <g:message code="user.password.label" default="Password" />
                    <span class="required-indicator">*</span>
                </label>
                <g:field type="password" name="newPassword" required="" value="${changePasswordCommandInstance?.newPassword}"/>
            </div>
            <div class="fieldcontain ${hasErrors(bean: changePasswordCommandInstance, field: 'repeatedNewPassword', 'error')} required">
                <label for="repeatedNewPassword">
                    <g:message code="user.repeatedPassword.label" default="Repeat password" />
                    <span class="required-indicator">*</span>
                </label>
                <g:field type="password" name="repeatedNewPassword" required="" value="${changePasswordCommandInstance?.repeatedNewPassword}"/>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:actionSubmit class="save" action="updatePassword" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
