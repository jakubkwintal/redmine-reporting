INSERT INTO TEST_SCENARIOS (CAMPAIGN_ID, TS_ID, TS_NAME, TS_STATUS, TS_TYPE) VALUES ("20218", "20300", "01.01. MainPage", "Waiting", "Aut");
INSERT INTO TEST_SCENARIOS (CAMPAIGN_ID, TS_ID, TS_NAME, TS_STATUS, TS_TYPE) VALUES ("20218", "20310", "02.01. SearchPage", "Waiting", "Aut");


INSERT INTO TEST_CASES (TS_ID, TC_NAME, TC_CHECKBOX_ID) VALUES  ("20300", "TC0. Check all the mandatory items on the main page", "311");
INSERT INTO TEST_CASES (TS_ID, TC_NAME, TC_CHECKBOX_ID) VALUES  ("20300", "TC1. Search for Depeche Mode in music category", "312");
INSERT INTO TEST_CASES (TS_ID, TC_NAME, TC_CHECKBOX_ID) VALUES  ("20310", "TC0. Search for U2's 'No line on the horizon' album with some details", "321");

COMMIT;