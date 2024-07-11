<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="static java.lang.System.*" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-05-29
  Time: 오후 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write("   <tr>");
            out.write("        <td>" + member.getId() + "</td>");
            out.write("        <td>" + member.getUsername() + "</td>");
            out.write("        <td>" + member.getAge() + "</td>");
            out.write("   </tr>");
        }
    %>
    </tbody>
</table>

</body>
</html>
