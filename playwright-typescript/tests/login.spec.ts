import { test as base } from '@playwright/test';
import { LoginPage } from './pages/loginPage.js';

const test = base.extend<{
   loginPage: LoginPage;
}>({
  loginPage: async ({ page }, use) => {
  await use(new LoginPage(page));
  },
});

test('Login User with correct data', async ({ loginPage }) => {
  await loginPage.startLogin();
  await loginPage.expectLoginSuccess();
});

test('Login user with wrong data', async ({  loginPage }) => {
  await loginPage.wrongLogin();
  await loginPage.expectLoginFailure();
});

test('Logout user', async ({ loginPage }) => {
  await loginPage.startLogout();
  await loginPage.expectLogoutSuccess();
});
