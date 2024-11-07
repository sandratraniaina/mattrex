CREATE VIEW
    foam_with_dimensions AS
SELECT
    f.*,
    d.width,
    d.height,
    d.depth
FROM
    foam f
    JOIN dimension d ON f.dimension_id = d.id;

CREATE VIEW
    size_type_with_dimensions AS
SELECT
    st.*,
    d.width,
    d.height,
    d.depth
FROM
    size_type st
    JOIN dimension d ON st.block_id = d.id;