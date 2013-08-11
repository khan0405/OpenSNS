<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%><%@ include file="taglibs.jsp"%><%@ page import="com.khan.opensns.model.*" %><% 
%>		<!-- Navigation -->
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container logo">
					<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="brand" href="#"><i class="icon-opensns"> </i>OpenSNS</a>
					<div class="nav-collapse collapse">
						<ul class="nav pull-right">
							<security:authentication property="Principal" var="user"/>
							<security:authorize ifNotGranted="ROLE_USER">
								<li>
									<a href="#overlay-signin" data-toggle="modal">Sign in</a>
								</li>
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_USER">
								<li class="profile-link">
									<a href="#">
										<img class="img-circle profile-thumb" src="${user.getUserInfo().profileImageThumb}"  />
									</a>
								</li>
								<li>
									<a href="#about">About</a>
								</li>
								<li class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" href="#contact"> Setting <b class="caret"></b> </a>
									<ul class="dropdown-menu">
										<li>
											<a href="#">계정 설정</a>
										</li>
										<li>
											<a href="#">link</a>
										</li>
										<li>
											<a href="#">link</a>
										</li>
									</ul>
								</li>
								<li>
									<a href="/logout">Sign out</a>
								</li>
							</security:authorize>
						</ul>
					</div>
				</div>
			</div>
		</div> <!-- Navigation end -->

	<security:authorize ifNotGranted="ROLE_USER">
		<!-- sign in dialog -->
		<div id="overlay-signin" class="modal hide fade" data-remote="assets/includes/signin.html">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					<i class="icon-remove"></i>
				</button>
				<h3 class="form-signin-heading">Please sign in</h3>
			</div>
			
			<div class="modal-body">
				<p>One fine body…</p>
            </div>
            
			<div class="modal-footer">
				<div class="pull-left">
					<a href="#overlay-forgot-password" data-dismiss="modal" data-toggle="modal"><i class="icon-chevron-left"></i> Forgot Password</a>
				</div>
				<div class="pull-right">
					<a href="#overlay-signup" data-dismiss="modal" data-toggle="modal">Register <i class="icon-chevron-right"></i></a>
				</div>
			</div>
		</div>
		
		<!-- forgot password dialog -->
    	<div id="overlay-forgot-password" class="modal hide fade" data-remote="assets/includes/forgotpassword.html">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					<i class="icon-remove"></i>
				</button>
				<h3 class="form-signin-heading">Forgot Password?</h3>
			</div>
			
			<div class="modal-body">
				<p>Data....</p>
			</div>
			
			<div class="modal-footer">
				<div class="pull-left">
					<a href="#overlay-signin" data-dismiss="modal" data-toggle="modal"><i class="icon-chevron-left"></i> Sign in</a>
				</div>
				<div class="pull-right">
					<a href="#overlay-signup" data-dismiss="modal" data-toggle="modal">Sign up <i class="icon-chevron-right"></i></a>
				</div>
			</div>
		</div>
		
		<!-- sign up dialog -->
    	<div id="overlay-signup" class="modal hide fade" header-remote="assets/includes/register.html" data-remote="assets/includes/register.html">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					<i class="icon-remove"></i>
				</button>
				<h3 class="form-signin-heading">Please Register</h3>
			</div>
			
			<div class="modal-body">
				<p>Data....</p>
			</div>
			
			<div class="modal-footer">
				<div class="pull-left">
					<a href="#overlay-forgot-password" data-dismiss="modal" data-toggle="modal"><i class="icon-chevron-left"></i> Forgot Password</a>
				</div>
				<div class="pull-right">
					<a href="#overlay-signin" data-dismiss="modal" data-toggle="modal">Sign in <i class="icon-chevron-right"></i></a>
				</div>
			</div>
		</div>
		
		<!-- update info dialog -->
		<div id="overlay-signup-2" class="modal hide fade" header-remote="assets/includes/register.html" data-remote="assets/includes/register.html">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					<i class="icon-remove"></i>
				</button>
				<h3 class="form-signin-heading">Please Register</h3>
			</div>
			
			<div class="modal-body">
				<p>Data....</p>
			</div>
			
			<div class="modal-footer">
				<div class="pull-left">
					<a href="#overlay-forgot-password" data-dismiss="modal" data-toggle="modal"><i class="icon-chevron-left"></i> Forgot Password</a>
				</div>
				<div class="pull-right">
					<a href="#overlay-signin" data-dismiss="modal" data-toggle="modal">Sign in <i class="icon-chevron-right"></i></a>
				</div>
			</div>
		</div>
		
		
	</security:authorize>