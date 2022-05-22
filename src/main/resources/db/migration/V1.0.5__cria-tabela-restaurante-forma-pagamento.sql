create table restaurante_forma_pagamento(
    restaurante_id             int references restaurante not null,
    forma_pagamento_id         int references forma_pagamento not null
);