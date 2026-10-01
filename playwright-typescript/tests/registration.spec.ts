import { test, expect } from '@playwright/test';
import { SignupPage } from './pages/signupPage.js';
import { generateUser } from './utils/faker.data.js';
import { sign } from 'node:crypto';


test('Register a user', async ({ page }) => {
const user = generateUser();    
const signupPage = new SignupPage(page);

await signupPage.startSignup(user);
await signupPage.fillAccountInfo(user);
await signupPage.fillAddressInfo(user);
await signupPage.expectAccountCreated();

});