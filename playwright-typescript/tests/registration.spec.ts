import { test, expect } from '@playwright/test';
import { SignupPage, type SignupUser } from './pages/signupPage.js';
import { generateUser } from './utils/faker.data.js';

test.describe('User Registration Tests', () => {
    let signupPage: SignupPage;
    let user: SignupUser;

test.beforeEach(async ({ page }) => {
    signupPage = new SignupPage(page);
    user = generateUser();
});

test('Register a user', async ({ page }) => {
    await signupPage.startSignup(user);
    await signupPage.fillAccountInfo(user);
    await signupPage.fillAddressInfo(user);
    await signupPage.expectAccountCreated();
});

test ('Register with existing e-mail', async ({ page }) => {
    await signupPage.signupWithUsedEmail(user);
    await signupPage.expectEmailAlreadyExists();
    });

