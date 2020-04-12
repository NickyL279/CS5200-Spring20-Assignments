<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
    <xsl:template match="website">
        <html>
            <body>
                <h1>Website</h1>
                <h2>
                    Pages
                    <br/>
                    <xsl:apply-templates select="/page"/>
                    Widgets
                </h2>

                <table border="1">
                    <tr>
                        <th>id</th>
                        <th>type</th>
                        <th>text</th>
                        <th>src</th>
                        <th>url</th>
                        <th>label</th>
                    </tr>
                    <xsl:apply-templates select="page/widget"/>

                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="/page">

        <xsl:value-of select="@name"></xsl:value-of>,
        Lorem ipsum
        <br/>
    </xsl:template>


    <xsl:template match="page/widget">
        <xsl:for-each-group group-by="id"
                            select="widget">
        </xsl:for-each-group>
        <tr>
            <td>
                <xsl:for-each select="current-group()">
                    <xsl:value-of select="@id"/>
                </xsl:for-each>
            </td>
            <td>
                2
            </td>
            <td>
                3
            </td>
            <td>
                4
            </td>
            <td>
                5
            </td>
            <td>
                6
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>