<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="head.jsp" %>  <!-- 头部已经引入jquery -->

<script type="text/javascript" src="js/index.js" ></script>



			
			<TABLE  cellSpacing=0 cellPadding=0 width="96%" align=center border=0 >
				<TBODY>
					<TR>
						<TD>

						<TABLE cellSpacing=1 cellPadding=1 width="100%" align=center
							bgColor=#c0c0c0 border=0>
							<TBODY>
								<TR bgColor=#dadada>

									<TD width="100%" align="right"><a href=" shopCart.jsp  "><img
										src="images/lcart_cn.gif" border=0></a></TD>
								</TR>
							</TBODY>
						</TABLE>
						<BR>
						<TABLE cellSpacing=2 cellPadding=1 width="100%" align=center
							border=0>
							
							<!-- 都使用了ajax -->
							<TBODY  id="foodlist" >
									
									
																									
							</TBODY>
						</TABLE>
						
						    <div id="pagediv" align=center > </div>
    								
    						
						
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
    

   
    
<%@ include file="bottom.jsp" %> 
    