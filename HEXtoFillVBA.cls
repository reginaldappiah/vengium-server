VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
END
Attribute VB_Name = "ThisWorkbook"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = True
Private Sub Workbook_Activate()
Call Color
End Sub
Sub Color()
  Dim listOfCells As Range, singleCell As Range, tHex As String
  
  Lastrow = ThisWorkbook.Sheets("Sheet1").Cells(Rows.Count, 1).End(xlUp).Row
  
  If TypeName(Selection) = "Range" Then
  Set listOfCells = Range("a1:c" & Lastrow)
    For Each singleCell In listOfCells
      tHex = Mid(singleCell.Text, 6, 2) & Mid(singleCell.Text, 4, 2) & Mid(singleCell.Text, 2, 2)
      singleCell.Interior.Color = WorksheetFunction.Hex2Dec(tHex)
    Next singleCell
  End If
End Sub

