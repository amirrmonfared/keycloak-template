<#import "template.ftl" as layout>
<@layout.emailLayout>
    <p>Please verify your email by clicking the link below:</p>
    <p><a href="${link}">Verify Email</a></p>
    <p>This link will expire in ${linkExpiration} minutes.</p>
</@layout.emailLayout>
