<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%><%@ include file="taglibs.jsp"%>
<c:forEach items="${USER_MENU}" var="menu"><% 
	%><c:if test="${menu.matcher.matches(pageContext.request)}" ><% 
		%><c:if test="${menu.id gt 1}"><c:out value=" > " /></c:if><% 
		%> <a class="current" href="${menu.url}">${menu.name}</a><% 
	%></c:if><% 
%></c:forEach> <%-- 
<c:choose>
	<c:when test="${topMenu == HOME_MENU_ID}">
		<c:choose>
			<c:when test="${empty subMenu}">
				<a class="current" href="<c:url value='${HOME_MENU_URL}'/>"><c:out
					value='${HOME_MENU_NAME}' /></a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='${HOME_MENU_URL}'/>"><c:out
					value='${HOME_MENU_NAME}' /></a>
				&gt; <a class="current"
					href="<c:url value='${SUB_MENU[topIdx][subIdx][2]}'/>"><c:out
					value='${SUB_MENU[topIdx][subIdx][1]}' /></a>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='${HOME_MENU_URL}'/>"><c:out
			value='${HOME_MENU_NAME}' /></a>
		<c:if test="${not empty topMenu}">
			<c:choose>
				<c:when test="${empty subMenu}">
				&gt; <a class="current"
						href="<c:url value='${TOP_MENU[topIdx][2]}'/>"><c:out
						value='${TOP_MENU[topIdx][1]}' /></a>
				</c:when>
				<c:otherwise>
				&gt; <a href="<c:url value='${TOP_MENU[topIdx][2]}'/>"><c:out
						value='${TOP_MENU[topIdx][1]}' /></a>
				&gt; <a class="current"
						href="<c:url value='${SUB_MENU[topIdx][subIdx][2]}'/>"><c:out
						value='${SUB_MENU[topIdx][subIdx][1]}' /></a>
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:otherwise>
</c:choose>
--%>