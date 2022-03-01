/*¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯*/
/* TODAS AS FUNÇÕES DESCRITAS AQUI DEVEM TERMINAR COM barra/asterisco*asterisco*barra/ PARA SINALIZAR FINALIZAÇÃO DO SCRIPT */
/*-------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Criar extension que remove acentos das palavras para comparações com like
 */
CREATE EXTENSION IF NOT EXISTS unaccent;/**/

/*
 * Inclui varias funções que retornam tabelas
 */
CREATE EXTENSION IF NOT EXISTS tablefunc;/**/

/**
 * Criar função de índice para transformar arrays em elementos ordenáveis por ORDER BY
 */
CREATE OR REPLACE FUNCTION idx(anyarray, anyelement)
  RETURNS int AS
$$
  SELECT i FROM (
     SELECT generate_series(array_lower($1,1),array_upper($1,1))
  ) g(i)
  WHERE $1[i] = $2
  LIMIT 1;
$$ LANGUAGE sql IMMUTABLE;/**/

/**
 * remover view para criar novo modelo
 */
drop view if exists dic; /**/

/**
 * Criar dicionário de dados de todas as tabelas do sistema
 */
create or replace view dic as
SELECT tbl.relname AS Tabela,
    atr.attname AS Coluna,
    pg_catalog.format_type(atr.atttypid,atr.atttypmod) AS Tipo,
    CASE WHEN (atr.atttypmod > 0) THEN atr.atttypmod-4 END AS Tamanho,
    CASE WHEN atr.attnotnull = 't' THEN 'Sim' ELSE '-' END AS Obrigatorio,
    coalesce(
        (select 'Sim' || ''
           from pg_constraint ct
          where ct.contype = 'p'
            and ct.conrelid = tbl.oid
            AND atr.attnum = ANY (ct.conkey)), '-') as ChavePrimaria,
    coalesce(
        (select 'Ref: ' || g.relname
           from pg_class g
          inner join pg_constraint ct on g.oid = ct.confrelid
          where ct.conrelid = tbl.oid
            AND atr.attnum = ANY (ct.conkey)), '-') as ChaveEstrangeira,
    coalesce(
        (select 'Sim' || ''
           from pg_constraint ct
          where ct.contype = 'u'
            and ct.conrelid = tbl.oid
            AND atr.attnum = ANY (ct.conkey)), '-') as ValorUnico
  FROM pg_attribute atr
 INNER JOIN pg_class tbl ON tbl.oid = atr.attrelid
  LEFT JOIN pg_attrdef atrdef ON atrdef.adrelid = tbl.oid AND atrdef.adnum = atr.attnum
  LEFT JOIN pg_namespace nsp ON nsp.oid = tbl.relnamespace
 WHERE tbl.relkind = 'r'::char
   AND nsp.nspname = 'public'
   AND atr.attnum > 0
 order by Tabela, ChavePrimaria desc, ChaveEstrangeira desc, Coluna;
/**/