import { test as base } from '@playwright/test';
import { SignupPage, type SignupUser } from './pages/signupPage.js';
import { generateUser } from './utils/faker.data.js';

const test = base.extend<{
  user: SignupUser;
  signupPage: SignupPage;
    }>({
  user: async ({}, use) => {
    await use(generateUser());
  },
  signupPage: async ({ page }, use) => {
    await use(new SignupPage(page));
  },
});

test('Register a user', async ({ signupPage, user }) => {
  await signupPage.startSignup(user);
  await signupPage.fillAccountInfo(user);
  await signupPage.fillAddressInfo(user);
  await signupPage.expectAccountCreated();
});

test('Register with existing e-mail', async ({ signupPage, user }) => {
  await signupPage.signupWithUsedEmail(user);
  await signupPage.expectEmailAlreadyExists();
});