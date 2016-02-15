<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:order="http://www.softproject.com.pl/lilu/model/order">
	<xsl:output method="xml" version="1.0" encoding="windows-1250" indent="yes"/>
        
        <xsl:decimal-format name="d" decimal-separator="," grouping-separator="."/>
        
	<xsl:template match="order:order">
		<LIST>
			<SENDER_ID>2317297</SENDER_ID>
			<SENDER_NAME>LILOU SP.ZO.O.</SENDER_NAME>
			<SENDER_POSTCODE>02-032</SENDER_POSTCODE>
			<SENDER_CITY>WARSZAWA</SENDER_CITY>
			<SENDER_STREET>FILTROWA 20/8</SENDER_STREET>
			<SENDER_HOUSENUMBER></SENDER_HOUSENUMBER>
			<SENDER_TEL>*********</SENDER_TEL>			
			<RECEIVER_NAME><xsl:value-of select="customer/name"/><xsl:text> </xsl:text><xsl:value-of select="customer/last-name"/></RECEIVER_NAME>
			<RECEIVER_POSTCODE><xsl:value-of select="delivery-address/post-code"/></RECEIVER_POSTCODE>
			<RECEIVER_CITY><xsl:value-of select="delivery-address/city"/></RECEIVER_CITY>
			<RECEIVER_STREET><xsl:value-of select="delivery-address/street"/></RECEIVER_STREET>			
			<RECEIVER_TEL><xsl:value-of select="customer/phone"/></RECEIVER_TEL>			
			<PRE_REC_EMAIL><xsl:value-of select="customer/email"/></PRE_REC_EMAIL>
			<PRODUCT>AH</PRODUCT>
			<INVOICE_TO>N</INVOICE_TO>
			<PAYMENT_TYPE>P</PAYMENT_TYPE>
                        <xsl:if test="transport/type = 'DHL-CASH-ON'">
                            <COD_REFERENCE><xsl:value-of select="order-id"/></COD_REFERENCE>
                            <CASH_ON_DELIVERY><xsl:value-of select="format-number(worthe,'###.###,00', 'd')"/></CASH_ON_DELIVERY>
			</xsl:if>
                        <DOCUMENT>1</DOCUMENT>
			<CATEGORY1>0</CATEGORY1>
			<CATEGORY2>0</CATEGORY2>
			<CATEGORY3>0</CATEGORY3>
			<CATEGORY4>0</CATEGORY4>			
			<COSTS_CENTER>COST CENTRE</COSTS_CENTER>
			<COMMENT><xsl:value-of select="customer/company-name"/><xsl:text> </xsl:text><xsl:value-of select="comments/comment[type='Dostawa']/value"/></COMMENT>
			<CONTENT/>
			<BLP>1</BLP>

		</LIST>
	</xsl:template>
</xsl:stylesheet>
