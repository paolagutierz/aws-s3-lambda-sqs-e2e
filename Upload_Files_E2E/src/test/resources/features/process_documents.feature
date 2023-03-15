Feature: Process Files
  I want to send a pdf file to process
  So I can validate the in SQS if the file was uploaded successfully

  Scenario: Send file liquidacion.pdf to S3 bucket
    When I send a pdf file liquidacion.pdf
    Then I should see the message in SQS contains the file name liquidacion
