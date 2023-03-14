Feature: Process Fragments
  I want to send fragments to process
  So I can validate the result of information extracted

  Scenario: Send fragment A to process by rues_lab_id model
    When I send a fragment type a to process with rues_lab_id model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send fragment B to process by rues_lab_id model
    When I send a fragment type b to process with rues_lab_id model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send fragment C to process by rues_lab_id model
    When I send a fragment type c to process with rues_lab_id model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send fragment D to process by rues_lab_id model
    When I send a fragment type d to process with rues_lab_id model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send fragment E to process by rues_lab_id model
    When I send a fragment type e to process with rues_lab_id model
    And I ask for the result of processing
    Then I should see the extracted information

  Scenario: Send fragment P to process by rues_lab_id model
    When I send a fragment type p to process with rues_lab_id model
    And I ask for the result of processing
    Then I should see the extracted information
