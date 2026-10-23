insert into users (
   username,
   password_hash
)
values
    ('admin', 'passwordhashhere')
on conflict (username) do nothing;

insert into currency (
    code,
    name,
    symbol
)
values
    ('PHP', 'Philippine Peso', '₱'),
    ('USD', 'US Dollar', '$'),
    ('EUR', 'Euro', '€'),
    ('GBP', 'British Pound', '£'),
    ('JPY', 'Japanese Yen', '¥'),
    ('CNY', 'Chinese Yuan', '¥'),
    ('SGD', 'Singapore Dollar', 'S$'),
    ('AUD', 'Australian Dollar', 'A$'),
    ('CAD', 'Canadian Dollar', 'C$'),
    ('INR', 'Indian Rupee', '₹'),
    ('KRW', 'South Korean Won', '₩'),
    ('IDR', 'Indonesian Rupiah', 'Rp'),
    ('MYR', 'Malaysian Ringgit', 'RM'),
    ('THB', 'Thai Baht', '฿'),
    ('VND', 'Vietnamese Dong', '₫'),
    ('HKD', 'Hong Kong Dollar', 'HK$'),
    ('NZD', 'New Zealand Dollar', 'NZ$'),
    ('CHF', 'Swiss Franc', 'CHF'),
    ('AED', 'UAE Dirham', 'د.إ'),
    ('SAR', 'Saudi Riyal', 'ر.س')
    on conflict (code) do nothing;