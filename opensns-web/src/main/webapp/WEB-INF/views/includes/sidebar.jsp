<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%><%@ include file="taglibs.jsp"%>
	<c:if test="${USER_MENU != null}">
		<ul class="mainmenu">
		<c:forEach items="${USER_MENU}" var="menu">
			<li>
				<a <c:if test="${CURR_MENU eq menu.id}">class="current"</c:if> href="${menu.url}">${menu.name}</a>
				<c:if test="${CURR_MENU == menu.id && USER_SUB_MENU != null}">
				<ul class="submenu">
				<c:forEach items="${USER_SUB_MENU}" var="subMenu">
					<li><a href="${subMenu.url}">${subMenu.name}</a></li>
				</c:forEach>
				</ul>
				</c:if>
			</li>
		</c:forEach>
		</ul>
	</c:if>
