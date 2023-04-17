Feature: Resize jpg image

  Scenario: Upload image exampleImage.jpg to S3 bucket
    When I upload the image exampleImage.jpg to s3 bucket
    And Download the resize image from the destination bucket
    Then I should see that the downloaded image has a smaller size than the uploaded image
