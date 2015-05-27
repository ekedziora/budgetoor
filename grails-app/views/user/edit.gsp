<%@ page import="budgetoor.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-user" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${userInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:userInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${userInstance?.version}" />
				<fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'login', 'error')} required">
                        <label for="login">
                            <g:message code="user.login.label" default="Login" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="login" required="" value="${userInstance?.login}"/>

                    </div>

                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
                        <label for="firstName">
                            <g:message code="user.firstName.label" default="First Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="firstName" required="" value="${userInstance?.firstName}"/>

                    </div>

                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
                        <label for="lastName">
                            <g:message code="user.lastName.label" default="Last Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="lastName" required="" value="${userInstance?.lastName}"/>

                    </div>

                    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'admin', 'error')} ">
                        <label for="admin">
                            <g:message code="user.admin.label" default="Admin" />

                        </label>
                        <g:checkBox name="admin" value="${userInstance?.admin}" />

                    </div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
