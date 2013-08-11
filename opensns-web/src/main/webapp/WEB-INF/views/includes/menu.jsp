<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="taglibs.jsp"%>
<%!
	static final String HOME_MENU_ID = "home";
	static final String HOME_MENU_NAME = "Home";
	static final String HOME_MENU_URL = "/";

	static final String[][] TOP_MENU = { // 순서 중요
	    {"home", "Home", "/"},
	    {"users", "회원 정보 관리", "/users"},
	    {"stores", "스토어 상품 관리", "/stores"},
	    {"ads", "광고 관리", "/ads"},
	    {"nfc", "NFC 관리", "/nfc"},
	    {"refunds", "환급 요청 관리", "/refunds"},
	    {"stats", "통계 관리", "/stats"},
	    {"service", "서비스 관리", "/service"},
	    {"stats", "CMS 관리자 설정", "/admin"}
	};

	static final String[][][] SUB_MENU = { // 순서 중요 (TOP_MENU 순서대로)
	    {
		    //{"01", "Submenu01", "/submenu01"},
		    //{"02", "Submenu02", "/submenu02"}
	    },
	    {
		    {"11", "회원 정보 관리", "/users"}
	    },
	    {
		    {"21", "스토어 상품 관리", "/stores"}
		    //{"22", "상용 애플리케이션", "/apps?action=onsaleList"},
		    //{"23", "개발 애플리케이션", "/apps?action=offsaleList"}	        
	    },
	    {
		    {"31", "광고 관리", "/ads"}
		    //,{"32", "Promotion 광고", "/promotionppiads"}	        
	    }, 
	    {
	        {"41", "NFC 관리", "/nfc"}
	    }, 
	    {
	        {"51", "환급 요청 관리", "/refunds"}
	    },
	    {
	        {"61", "통계 관리", "/stats"}
	    },
	    {
	        {"71", "서비스 관리", "/service"}
	    },
	    {
	        {"81", "CMS 관리자 설정", "/admin"}
	    }
	};

	/*
	static java.util.Map TOP_MENU_MAP = new java.util.HashMap();
	
	static java.util.Map SUB_MENU_MAP = new java.util.HashMap();
	
	static synchronized java.util.Map getTopMenuMap() {
	    if (TOP_MENU_MAP == null) {
	        TOP_MENU_MAP = new java.util.HashMap();
	    }
	    if (TOP_MENU_MAP.size() == 0) {
	        for (int i = 0; i < TOP_MENU.length; i++) {
	            TOP_MENU_MAP.put(TOP_MENU[i][0], TOP_MENU[i]);
	        }
	        System.out.println("TOP_MENU_MAP created.");
	    }
	    return TOP_MENU_MAP;        
	}
	
	static synchronized java.util.Map getSubMenuMap() {
	    if (SUB_MENU_MAP == null) {
	        SUB_MENU_MAP = new java.util.HashMap();
	    }
	    if (SUB_MENU_MAP.size() == 0) {
	        for (int i = 0; i < TOP_MENU.length; i++) {
	            SUB_MENU_MAP.put(TOP_MENU[i][0], SUB_MENU[i]);
	        }
	        System.out.println("SUB_MENU_MAP created.");
	    }
	    return SUB_MENU_MAP;        
	}
	*/
%>
<%
	/* left.jsp, indicator.jsp 에서 사용됨 */
	request.setAttribute("HOME_MENU_ID", HOME_MENU_ID);
	request.setAttribute("HOME_MENU_NAME", HOME_MENU_NAME);
	request.setAttribute("HOME_MENU_URL", HOME_MENU_URL);
	request.setAttribute("TOP_MENU", TOP_MENU);
	request.setAttribute("SUB_MENU", SUB_MENU);
	//request.setAttribute("TOP_MENU_MAP", getTopMenuMap());
	//request.setAttribute("SUB_MENU_MAP", getSubMenuMap());
	
	/* indicator.jap 에서 사용됨 */
    String topMenu = (String) request.getAttribute("topMenu");
    String subMenu = (String) request.getAttribute("subMenu");
    int topIdx = 0;
    int subIdx = 0;
    if (TOP_MENU != null && topMenu != null) {
        for (int i = 0; i < TOP_MENU.length; i++) {
            if (topMenu.equals(TOP_MENU[i][0])) {
                topIdx = i;
                break;
            }
        }
        if (SUB_MENU != null && subMenu != null) {
            for (int i = 0; i < SUB_MENU[topIdx].length; i++) {
                if (subMenu.equals(SUB_MENU[topIdx][i][0])) {
                    subIdx = i;
                    break;
                }
            }
        }
    }
    request.setAttribute("topIdx", new Integer(topIdx));
    request.setAttribute("subIdx", new Integer(subIdx));	
%>
	<c:if test="${USER_MENU != null}">
		<c:forEach items="${USER_MENU}" var="menu" varStatus="status">
		<c:if test="${status.first}">
			<c:out value='<ul>' escapeXml="false" />
		</c:if>
		
		<li>
			<a <c:if test="${topMenu eq menu.url}">class="<c:out value="current" />"</c:if> href="<c:url value='${menu.url}'/>"><c:out value='${menu.name}' /></a>
		</li>
		
		<c:if test="${status.last}">
			<c:out value='</ul>' escapeXml="false" />
		</c:if>
		</c:forEach>
	</c:if><%-- 

<c:forEach items="${TOP_MENU}" var="T_MENU" varStatus="status">
	<c:if test="${status.first}">
		<c:out value='<ul>' escapeXml="false" />
	</c:if>
	
	<li>
		<a <c:if test="${topMenu eq T_MENU[0]}">class="<c:out value="current" />"</c:if> href="<c:url value='${T_MENU[2]}'/>"><c:out value='${T_MENU[1]}' /></a>
	</li>
	<c:if test="${status.last}">
		<c:out value='</ul>' escapeXml="false" />
	</c:if>
</c:forEach>
--%>