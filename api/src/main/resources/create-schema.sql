create table if not exists users
(
    id bigint not null generated always as identity primary key,
    username varchar(100) not null unique,
    password_hash text not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table if not exists user_detail (
    user_id bigint not null references users(id) on delete cascade,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(255) unique,
    phone varchar(30)
);

create table if not exists budget_group (
    id bigint not null generated always as identity primary key,
    user_id bigint not null references users(id) on delete cascade,
    name varchar(100) not null
);

create table if not exists budget (
    id bigint not null generated always as identity primary key,
    budget_group_id bigint not null references budget_group(id) on delete cascade,
    name varchar(100) not null,
    amount numeric(12,2) not null check (amount >= 0),
    currency_code varchar(3) not null,
    status varchar(20) not null default 'ACTIVE'
        check (status in ('ACTIVE', 'COMPLETED', 'CANCELLED')),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create index if not exists idx_budget_group_id on budget(budget_group_id);

create table if not exists cart (
    id bigint not null generated always as identity primary key,
    budget_id bigint not null references budget(id) on delete cascade,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create index if not exists idx_cart_budget_id on cart(budget_id);


create table if not exists product (
    id bigint not null generated always as identity primary key,
    name varchar(100) not null,
    brand varchar(100) not null,
    category varchar(50),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
    );

create index if not exists idx_product_name on product(name);
create index if not exists idx_product_category on product(category);

create table if not exists cart_product (
    id bigint not null generated always as identity primary key,
    cart_id bigint not null references cart(id) on delete cascade,
    product_id bigint not null references product(id),
    quantity integer not null default 1,
    unit_price numeric(12,2) not null check (unit_price >= 0),
    source varchar(50),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create index if not exists idx_cart_product_cart_id on cart_product(cart_id);
create index if not exists  idx_cart_product_id on cart_product(product_id);

create table if not exists ocr_scan (
    id bigint generated always as identity primary key,
    user_id bigint not null references users(id) on delete cascade,
    image_url text not null,
    status varchar(20) not null default 'PENDING'
        check (status in ('PENDING', 'PROCESSING', 'COMPLETED', 'FAILED')),
    raw_text text,
    detected_product_name text,
    detected_brand_name text,
    detected_price numeric(12,2)
        check (detected_price >= 0),
    detected_currency varchar(3),
    confidence numeric(5,4),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create index if not exists idx_ocr_scan_id on ocr_scan(id);
create index if not exists idx_ocr_scan_status on ocr_scan(status);

create table if not exists currency (
    code varchar(10) not null primary key,
    name varchar(100) not null,
    symbol character(10) not null
);



