import dotenv from 'dotenv';

dotenv.config({ path: 'credentials.env' });

export function getUsedEmail(): string {
  const email = process.env.PLAYWRIGHT_USER;
  if (!email) {
    throw new Error('PLAYWRIGHT_USER não configurado no .env');
  }
  return email;
  
}

export function getPassword(): string {
  const password = process.env.PLAYWRIGHT_PASS;
  if (!password) {
    throw new Error('PLAYWRIGHT_PASSWORD não configurado no .env');
  }
  return password;
}