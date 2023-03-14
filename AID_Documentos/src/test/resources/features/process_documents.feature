Feature: Process Documents
  I want to send documents to process
  So I can validate the result of information extracted

  ### Reintentos ###

  Scenario: Send document pdf-reintentos to process by carta_laboral model
    When I send a document type pdf-reintentos to process with reintentos model
    And I ask for the result of processing
    Then I should see the extracted information

  ### flujo fallido ###

  Scenario: Send document pdf-flujo-fallido-CL to process by carta_laboral model
    When I send a document type pdf-flujo-fallido-CL to process with carta_laboral model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document flujo-fallido-pdf-DR to process by declaracion_renta model
    When I send a document type flujo-fallido-pdf-DR to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document caso-especial-pdf-DR to process by declaracion_renta model
    When I send a document type caso-especial-pdf-DR to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document tif-flujo-fallido-FO to process by full_ocr model
    When I send a document type tif-flujo-fallido-FO to process with full_ocr model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document png-cc-flujo-fallido to process by id_cards model
    When I send a document type png-cc-flujo-fallido to process with id_cards model
    And I ask for the result of processing
    Then I should see the extracted information


  #### carta laboral ####

  Scenario: Send document pdf_digital to process by carta_laboral model
    When I send a document type pdf_digital to process with carta_laboral model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document pdf_no_digital to process by carta_laboral model
    When I send a document type pdf_no_digital to process with carta_laboral model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document png to process by carta_laboral model
    When I send a document type png to process with carta_laboral model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document tif to process by carta_laboral model
    When I send a document type tif to process with carta_laboral model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document jpg to process by carta_laboral model
    When I send a document type jpg to process with carta_laboral model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document gif to process by carta_laboral model
    When I send a document type gif to process with carta_laboral model
    And I ask for the result of processing
    Then I should see the extracted information

########################################################
#  Scenario: Send document error to process by carta_laboral model
#    When I send a document type error to process with carta_laboral model
#    And I ask for the result of processing
#    Then I should see the extracted information
########################################################

 #### declaracion renta ####

# pdf

  Scenario: Send document 2019-110-pdf to process by declaracion_renta model
    When I send a document type 2019-110-pdf to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2020-210-pdf to process by declaracion_renta model
    When I send a document type 2020-210-pdf to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2021-110-pdf to process by declaracion_renta model
    When I send a document type 2021-110-pdf to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

# gif

  Scenario: Send document 2019-110-gif to process by declaracion_renta model
    When I send a document type 2019-110-gif to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2020-210-gif to process by declaracion_renta model
    When I send a document type 2020-210-gif to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2021-110-gif to process by declaracion_renta model
    When I send a document type 2021-110-gif to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

# png

  Scenario: Send document 2019-210-png to process by declaracion_renta model
    When I send a document type 2019-210-png to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2020-110-png to process by declaracion_renta model
    When I send a document type 2020-110-png to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2021-110-png to process by declaracion_renta model
    When I send a document type 2021-110-png to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

# tif

  Scenario: Send document 2019-210-tif to process by declaracion_renta model
    When I send a document type 2019-210-tif to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2020-110-tif to process by declaracion_renta model
    When I send a document type 2020-110-tif to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2021-210-tiff to process by declaracion_renta model
    When I send a document type 2021-210-tiff to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

# scan

  Scenario: Send document 2019-210-png-scan to process by declaracion_renta model
    When I send a document type 2019-210-png-scan to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document 2020-210-png-scan to process by declaracion_renta model
    When I send a document type 2020-210-png-scan to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

# jpg

  Scenario: Send document 2021-210-jpg to process by declaracion_renta model
    When I send a document type 2021-210-jpg to process with declaracion_renta model
    And I ask for the result of processing
    Then I should see the extracted information

########################################################
#  Scenario: Send document error to process by declaracion_renta model
#    When I send a document type error to process with declaracion_renta model
#    And I ask for the result of processing
#    Then I should see the extracted information
########################################################


 #### full ocr ####

  Scenario: Send document pdf-1-pag to process by full_ocr model
    When I send a document type pdf-1-pag to process with full_ocr model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document pdf-11-pag to process by full_ocr model
    When I send a document type pdf-11-pag to process with full_ocr model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document tif-32-pag to process by full_ocr model
    When I send a document type tif-32-pag to process with full_ocr model
    And I ask for the result of processing
    Then I should see the extracted information

  #### id cards ####

  Scenario: Send document tif-cc to process by id_cards model
    When I send a document type tif-cc to process with id_cards model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document pdf-co to process by id_cards model
    When I send a document type pdf-co to process with id_cards model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document png-ce to process by id_cards model
    When I send a document type png-ce to process with id_cards model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document png-ti to process by id_cards model
    When I send a document type png-ti to process with id_cards model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document pdf-ncc to process by id_cards model
    When I send a document type pdf-ncc to process with id_cards model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send document tif-ppt to process by id_cards model
    When I send a document type tif-ppt to process with id_cards model
    And I ask for the result of processing
    Then I should see the extracted information