import { test, expect } from '@playwright/test';
import { SignupPage, type SignupUser } from './pages/signupPage.js';
import { generateUser } from './utils/faker.data.js';

// test.describe('User Login Tests', () => {
//     let signupPage: SignupPage;
//     let user: SignupUser;