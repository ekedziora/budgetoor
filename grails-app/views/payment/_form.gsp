<%@ page import="budgetoor.Payment" %>



<div class="fieldcontain ${hasErrors(bean: paymentInstance, field: 'user', 'error')} required">
    <label for="user">
        <g:message code="user.label" default="User"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="user" name="user.id" from="${budgetoor.User.list()}" optionKey="id" required=""
              value="${paymentInstance?.user?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: paymentInstance, field: 'description', 'error')} required">
    <label for="description">
        <g:message code="payment.description.label" default="Description"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="description" required="" value="${paymentInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: paymentInstance, field: 'amount', 'error')} required">
    <label for="amount">
        <g:message code="payment.amount.label" default="Amount"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="amount" value="${fieldValue(bean: paymentInstance, field: 'amount')}" required=""/>

</div>

