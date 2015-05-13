<%@ page import="budgetoor.Payment" %>

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
    <g:field type="number decimal" min="0" pattern="${budgetoor.Payment.BIG_DECIMAL_REGEXP}" name="amount"
             value="${fieldValue(bean: paymentInstance, field: 'amount')}" required=""/>

</div>

